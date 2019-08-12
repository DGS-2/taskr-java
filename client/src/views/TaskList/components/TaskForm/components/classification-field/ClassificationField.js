import React, { Component } from 'react'
import PropTypes from 'prop-types';

import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

import { classifications } from '../../../../../../const/consts';

class ClassificationField extends Component {
    addClassification = e => {
        const { setItemClassification, index } = this.props;
        setItemClassification(e.target.value, index);
    }
    render() {
        const { classification, classes } = this.props;
        return (
            <div className={classes.wrapped}>
                <FormControl className={classes.formControl}>
                    <InputLabel htmlFor="classification">Classification</InputLabel>
                    <Select
                        value={classification}
                        onChange={this.addClassification}
                        inputProps={{
                            name: 'classification',
                            id: 'classification',
                        }}
                    >
                        {classifications.map(item => {
                            return <MenuItem key={item.value} value={item.value}>{item.label}</MenuItem>
                        })}
                    </Select>
                </FormControl>
            </div>
        )
    }
}

ClassificationField.propTypes = {
    setItemClassification: PropTypes.func.isRequired,
    classes: PropTypes.object.isRequired,
    className: PropTypes.string,
    classification: PropTypes.string
}

export default ClassificationField;