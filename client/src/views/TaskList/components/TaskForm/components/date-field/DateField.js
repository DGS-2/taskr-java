import React, { Component } from 'react'

import PropTypes from 'prop-types';

import 'date-fns';

import DateFnsUtils from '@date-io/date-fns';
import FormControl from '@material-ui/core/FormControl';
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';

class DateField extends Component {
    changeDueDate = date => {
        const { setDueDate, index } = this.props;
        setDueDate(date, index);
    }
    render() {
        const { classes, due } = this.props;
        return (
            <div className={classes.wrapped}>
                <FormControl className={classes.formControl}>
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <KeyboardDatePicker
                            margin="normal"
                            id="mui-pickers-date"
                            label="Set Due Date"
                            value={due}
                            onChange={this.changeDueDate}
                            KeyboardButtonProps={{
                                'aria-label': 'change date',
                            }}
                        />
                    </MuiPickersUtilsProvider>
                </FormControl>
            </div> 
        );
    }
}

DateField.propTypes = { 
    classes: PropTypes.object,
    due: PropTypes.object,
    setDueDate: PropTypes.func,
    index: PropTypes.number
}

export default DateField;