import React, { Component } from 'react'

import PropTypes from 'prop-types';

import TextField from '@material-ui/core/TextField';
import FormControl from '@material-ui/core/FormControl';

class TitleField extends Component {
    constructor(props){
        super(props);
        this.state = {
            title: ''
        };
    }
    onChange = e => {
        this.setState({[e.target.name]: e.target.value});
    }

    onKeyDown = e => {
        const { changeField, index } = this.props;
        if(e.keyCode === 13){
            changeField(this.state.title, index);
            this.setState({title: ''});
        }
    }

    render() {
        const { classes } = this.props;
        const { title } = this.state;
        return (
            <div className={classes.wrapped}>
                <FormControl className={classes.formControl}>
                    <TextField
                        className={classes.textField}
                        helperText="Please assign a title to this task. Press 'Enter' to save."
                        label="Task name"
                        margin="dense"
                        required
                        fullWidth
                        value={this.props.title !== '' ? this.props.title : title}
                        variant="outlined"
                        name="title"
                        onChange={this.onChange}
                        onKeyDown={this.onKeyDown}
                    />
                </FormControl>
            </div>
        )
    }
}

TitleField.propTypes = {
    changeField: PropTypes.func.isRequired,
    classes: PropTypes.object.isRequired,
    title: PropTypes.string,
    index: PropTypes.number
}

export default TitleField;