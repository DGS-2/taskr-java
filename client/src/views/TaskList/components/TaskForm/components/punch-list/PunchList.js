import React, { Component } from 'react'

import PropTypes from 'prop-types';

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Checkbox from '@material-ui/core/Checkbox';
import IconButton from '@material-ui/core/IconButton';
import CommentIcon from '@material-ui/icons/Comment';

class PunchList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            checked: []
        }
    }

    handleToggle = value => () => {
        const currentIndex = this.state.checked.indexOf(value);
        const newChecked = [...this.state.checked];
    
        if (currentIndex === -1) {
          newChecked.push(value);
        } else {
          newChecked.splice(currentIndex, 1);
        }
    
        this.setState({checked: newChecked});
      };

    render() {
        const { classes, currentWorkflow } = this.props;
        const { checked } = this.state;
        return (
            <List className={classes.checklist}>
                {currentWorkflow.map(value => {
                    console.log(value)
                const labelId = `checkbox-list-label-${value}`;

                return (
                    <ListItem key={value.name.replace(' ', '-')} role={undefined} dense button onClick={this.handleToggle(value)}>
                        <ListItemIcon>
                            <Checkbox
                                edge="start"
                                checked={checked.indexOf(value) !== -1}
                                tabIndex={-1}
                                disableRipple
                                inputProps={{ 'aria-labelledby': labelId }}
                            />
                        </ListItemIcon>
                        <ListItemText id={labelId} primary={`Workflow item ${value.name}`} />
                        <ListItemSecondaryAction>
                            <IconButton edge="end" aria-label="comments">
                                <CommentIcon />
                            </IconButton>
                        </ListItemSecondaryAction>
                    </ListItem>
                    );
                })}
            </List>
        )
    }
}

PunchList.propTypes = {
    items: PropTypes.array,
    classes: PropTypes.object,
    currentWorkflow: PropTypes.array
}

export default PunchList;