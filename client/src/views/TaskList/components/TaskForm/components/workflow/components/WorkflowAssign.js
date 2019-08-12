import React, { Component } from 'react'

import PropTypes from 'prop-types';

import FormControl from '@material-ui/core/FormControl';
import Autosuggest from 'react-autosuggest';
import match from 'autosuggest-highlight/match';
import parse from 'autosuggest-highlight/parse';
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import MenuItem from '@material-ui/core/MenuItem';

const people = [
    {
      first: 'Charlie',
      last: 'Brown',
      twitter: 'dancounsell'
    },
    {
      first: 'Charlotte',
      last: 'White',
      twitter: 'mtnmissy'
    },
    {
      first: 'Chloe',
      last: 'Jones',
      twitter: 'ladylexy'
    },
    {
      first: 'Cooper',
      last: 'King',
      twitter: 'steveodom'
    }
  ];
  
  // https://developer.mozilla.org/en/docs/Web/JavaScript/Guide/Regular_Expressions#Using_Special_Characters
  function escapeRegexCharacters(str) {
    return str.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
  }
  
  function getSuggestions(value) {
    const escapedValue = escapeRegexCharacters(value.trim());
    
    if (escapedValue === '') {
      return [];
    }
  
    const regex = new RegExp('\\b' + escapedValue, 'i');
    
    return people.filter(person => regex.test(getSuggestionValue(person)));
  }
  
  function getSuggestionValue(suggestion) {
    return `${suggestion.first} ${suggestion.last}`;
  }

  function renderInputComponent(inputProps) {
    const { classes, inputRef = () => {}, ref, ...other } = inputProps;
  
    return (
      <TextField
        fullWidth
        InputProps={{
          inputRef: node => {
            ref(node);
            inputRef(node);
          },
          classes: {
            input: classes.input,
          },
        }}
        {...other}
      />
    );
  }

class WorkflowAssign extends Component {
    constructor(props) {
        super(props);
    
        this.state = {
          value: '',
          suggestions: []
        };    
      }
    
      onChange = (e, { newValue, method }) => {
        
        this.setState({
          value : newValue
        });

        // this.props.addApprover(newValue, this.props.index);
      };
      
      onSuggestionsFetchRequested = ({ value }) => {
        this.setState({
          suggestions: getSuggestions(value)
        });
      };
    
      onSuggestionSelected = (event, { suggestion, suggestionValue, suggestionIndex, sectionIndex, method }) => {
          const { addApprover, index } = this.props;
          addApprover(suggestion, index);
      }

      onSuggestionsClearRequested = () => {
        this.setState({
          suggestions: []
        });
      };
    
      renderSuggestion(suggestion, { query, isHighlighted }) {
        const suggestionText = `${suggestion.first} ${suggestion.last}`;
        const matches = match(suggestionText, query);
        const parts = parse(suggestionText, matches);
    
        return (
            <MenuItem selected={isHighlighted} component="div">
              {parts.map((part) => (
                  <span key={part.text} style={{ fontWeight: part.highlight ? 500 : 400 }}>
                    {part.text}
                  </span>
              ))}
            </MenuItem>
        );
      }

      render() {
        const { value, suggestions } = this.state;
        const { classes, approver } = this.props;
        let approvingOfficial;
        if(approver === null ) approvingOfficial = value;
        else approvingOfficial = `${approver.first} ${approver.last}`;

        const inputProps = {
          placeholder: "Type 'c'",
          value: approvingOfficial,
          onChange: this.onChange,
          classes: classes,
          id: 'react-autosuggest-simple',
          label: 'Approver',
        };
    
        return (
          <div className={classes.wrapped}>
            <FormControl className={classes.formControl}>
              <Autosuggest
                  renderInputComponent={renderInputComponent}
                  suggestions={suggestions}
                  onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
                  onSuggestionsClearRequested={this.onSuggestionsClearRequested}
                  onSuggestionSelected={this.onSuggestionSelected}
                  getSuggestionValue={getSuggestionValue}
                  renderSuggestion={this.renderSuggestion}
                  inputProps={inputProps} 
                  theme={{
                      container: classes.container,
                      suggestionsContainerOpen: classes.suggestionsContainerOpen,
                      suggestionsList: classes.suggestionsList,
                      suggestion: classes.suggestion,
                  }}
                  renderSuggestionsContainer={options => (
                    <Paper {...options.containerProps} square>
                      {options.children}
                    </Paper>
                  )}
              />
            </FormControl>
              
          </div>
        );
      }
}

WorkflowAssign.propTypes = {
    classes: PropTypes.object,
    approver: PropTypes.object,
    index: PropTypes.number,
    addApprover: PropTypes.func
}

export default WorkflowAssign;