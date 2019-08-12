import React, { Component } from 'react'

import PropTypes from 'prop-types';

import FormControl from '@material-ui/core/FormControl';
import  TextField  from '@material-ui/core/TextField';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';

class DescriptionField extends Component {
    constructor(props) {
        super(props);
        this.state = {
            description: ''
        };
    }

    onChange = e => {
        let value = e.target.value;
        this.setState({[e.target.name]: value});
    }

    onClick = e => {
        const { description } = this.props;
        if(description !== '') this.setState({description: description});
    }

    setDescription = () => {
        const { addTaskDescription, index } = this.props;
        addTaskDescription(this.state.description, index);
    }

    render() {
        const { classes } = this.props;
        const { description } = this.state
        return (
            <div className={classes.wrapped}>
                <FormControl className={classes.formControl}>
                    <TextField
                        className={classes.textField}
                        label="Task description"
                        margin="dense"
                        required
                        multiline
                        fullWidth
                        value={ description === '' ? this.props.description : description }
                        variant="outlined"
                        name="description"
                        onChange={this.onChange}
                        onClick={this.onClick}
                    />
                    {description !== '' && (
                        <Button variant="contained" size="small" className={classes.button} onClick={this.setDescription}>
                            <SaveIcon className={classes.leftIcon} />
                            Save
                        </Button>
                    )}
                </FormControl>    
            </div>
        )
    }
}

DescriptionField.propTypes = {
    addTaskDescription: PropTypes.func.isRequired,
    classes: PropTypes.object.isRequired,
    index: PropTypes.number,
    description: PropTypes.string
}

export default DescriptionField;