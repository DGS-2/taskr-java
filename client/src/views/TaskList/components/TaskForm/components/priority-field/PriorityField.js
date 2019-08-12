import React, { Component } from 'react'

import PropTypes from 'prop-types';

import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

import { priorityOptions } from "../../../../../../const/consts";

class PriorityField extends Component {
    addPriority = e => {
        const { addPriorityToTask, index } = this.props;
        addPriorityToTask(e.target.value, index);
    }
    render() {
        const { priority, classes } = this.props;
        return (
            <div className={classes.wrapped}>
                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="priority">Priority</InputLabel>
                    <Select
                        value={priority}
                        onChange={this.addPriority}
                        inputProps={{
                            name: 'priority',
                            id: 'priority',
                        }}
                    >
                        {priorityOptions.map(item => {
                            return <MenuItem key={item.value} value={item.value}>{item.label}</MenuItem>
                        })}
                    </Select>
                </FormControl>
            </div>
        )
    }
}

PriorityField.propTypes = {
    addPriorityToTask: PropTypes.func.isRequired,
    classes: PropTypes.object.isRequired,
    priority: PropTypes.string,
    index: PropTypes.number
}

export default PriorityField;