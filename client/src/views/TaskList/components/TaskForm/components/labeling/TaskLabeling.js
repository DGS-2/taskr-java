import React, { Component } from 'react'

import PropTypes from 'prop-types';

import Chip from '@material-ui/core/Chip';
import TextField from '@material-ui/core/TextField';

class TaskLabeling extends Component {
    constructor(props){
        super(props);
        this.state = {
            tags: [],
            tag: ''
        }
    }

    onChange = e => {
        this.setState({[e.target.name]: e.target.value})
    }

    onKeyDown = e => {
        const { addLabelsToTask } = this.props;
        if(e.keyCode === 13){
            let tags = this.state.tags;
            tags.push({tag: this.state.tag});
            this.setState({tags: tags, tag: ''});
            addLabelsToTask(tags);
        }
    }

    handleDelete = chipToDelete => () => {
        const { addLabelsToTask, labels } = this.props;

        let arr = labels.filter(label => label.tag !== chipToDelete.tag);
        this.setState({tags: arr});
        addLabelsToTask(arr);
    }
    
    render() {
        const { classes, labels } = this.props;
        const { tag } = this.state;

        return (
            <div className={classes.field}>
                <div className={classes.center}>
                    <TextField
                        id="standard-name"
                        label="Add labels to task"
                        className={classes.input}
                        value={tag}
                        onChange={this.onChange}
                        onKeyDown={this.onKeyDown}
                        name="tag"
                        margin="normal"
                    />
                </div>
                <div className={classes.wrapped}>
                    {labels.map((label, index) => {
                        return <Chip
                                    key={index}
                                    label={label.tag}
                                    onDelete={this.handleDelete(label)}
                                    className={classes.chip}
                                    color="primary"
                                    name={label}
                                />
                    })}
                </div>
                
            </div>
        )
    }
}

TaskLabeling.propTypes = {
    classes: PropTypes.object,
    labels: PropTypes.array,
    addLabelsToTask: PropTypes.func
};

export default TaskLabeling;