import React, { Component } from 'react'

import PropTypes from 'prop-types';

import clsx from 'clsx';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelActions from '@material-ui/core/ExpansionPanelActions';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Chip from '@material-ui/core/Chip';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';

class WorkflowTask extends Component {
    constructor(props) {
        super(props);

        this.state = {
            title: '',
            description: '',
            due: new Date(),
            priority: '',
            files: []
        };
    }
    render() {
        const { classes } = this.props;
        return (
            <div>
                <ExpansionPanel>
                    <ExpansionPanelSummary
                        expandIcon={<ExpandMoreIcon />}
                        aria-controls="panel1c-content"
                        id="panel1c-header"
                    >
                    <div className={classes.column}>
                        <Typography className={classes.heading}>Location</Typography>
                    </div>
                    <div className={classes.column}>
                        <Typography className={classes.secondaryHeading}>Select trip destination</Typography>
                    </div>
                    </ExpansionPanelSummary>
                    <ExpansionPanelDetails className={classes.details}>
                        <div className={classes.column} />
                        <div className={classes.column}>
                            <Chip label="Barbados" onDelete={() => {}} />
                        </div>
                        <div className={clsx(classes.column, classes.helper)}>
                            <Typography variant="caption">
                            Select your destination of choice
                            <br />
                            </Typography>
                        </div>
                    </ExpansionPanelDetails>
                    <Divider />
                    <ExpansionPanelActions>
                        <Button size="small">Cancel</Button>
                        <Button size="small" color="primary">
                            Save
                        </Button>
                    </ExpansionPanelActions>
                </ExpansionPanel>
            </div>
        )
    }
}

WorkflowTask.propTypes = {
    index: PropTypes.number,
    classes: PropTypes.object,
    addTaskToWorkflow: PropTypes.func
}

export default WorkflowTask;