import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';

import compose from 'recompose/compose';
// Actions
import { addTask } from "../../../../actions/taskActions";

import Button from '@material-ui/core/Button';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Typography from '@material-ui/core/Typography';

// Material Helpers
import { withStyles } from '@material-ui/core';

import { filterUsers } from "../../../../functions/functions";

import Workflow from './components/workflow/Workflow';
import PunchList from './components/punch-list/PunchList';
import TaskLabeling from './components/labeling/TaskLabeling';

// Component styles
import styles from './styles';

import {
    Portlet,
    PortletHeader,
    PortletLabel,
    PortletContent,
    PortletFooter
} from '../../../../components';

class TaskForm extends Component {
    constructor(props){
        super(props)    
        this.state = {
          errors: {},
          taggableUsers: [],
          suggestions: [],
          files: [],
          parent: '',
          activeStep: 0,
          currentWorkflow: [],
          tags: []
        }
      }
    
      componentWillReceiveProps(newProps) {
        if(newProps.errors) {
          this.setState({errors: newProps.errors})
        }
      }
    
      onSubmit = e => {
        e.preventDefault();   
      }
    
      buildTask = user => {
        // const { profile } = this.props;
        // let taskCreator = profile.profile.user._id;
        // const newTask = {
        //   classification: this.state.classification,
        //   title: this.state.title,
        //   description: this.state.description,
        //   files: this.state.files,
        //   createdOn: new Date(),
        //   dueBy: this.state.due,
        //   createdBy: taskCreator,
        //   assignedTo: user._id,
        //   parentTask: this.state.parent,
        //   priority: this.state.priority
        // }
        console.log(this.state);
        // this.props.addTask(newTask, this.props.history);
      }

      handleNext = () => {
        this.setState({activeStep: this.state.activeStep + 1});
      }
    
      handleBack = () => {
          this.setState({activeStep: this.state.activeStep - 1});
      }
      
      handleReset = () => {
          this.setState({activeStep: 0});
      }

      updateWorkflow = arr => {
        this.setState({currentWorkflow : arr});
      }

      labelTask = arr => {
        this.setState({tags: arr}, console.log(this.state.tags));
      }

      getSteps = () => {
        return ['Define a workflow', 'Add a checklist', 'Help us label this workflow', 'Let\'s review and save'];
      }
      
      getStepContent = step => {
        switch (step) {
          case 0:
            return 'Attach a workflow or build a new one.';
          case 1:
            return 'Add checklist items for this task.';
          case 2:
            return 'Add labels to this workflow.';
          case 3:
            return 'Review the task and save';
          default:
            return 'Unknown step';
        }
      }

      render() {
        const { classes } = this.props;
        const { activeStep } = this.state;
        const steps = this.getSteps();

        return (
          <form
            autoComplete="off"
            noValidate
            onSubmit={this.onSubmit}
          >
            <Portlet>
              <PortletHeader>
                <PortletLabel
                  title="Create a new workflow"
                />
              </PortletHeader>              
              <PortletContent noPadding>
                <Stepper activeStep={activeStep}>
                    {steps.map((label, i) => {
                        return (
                            <Step key={i}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        )
                    })}
                </Stepper>
                <div>
                  {activeStep === steps.length ? (
                      <div>
                          <Typography className={classes.instructions}>
                              All steps completed - you&apos;re finished
                          </Typography>
                          <Button onClick={this.handleReset} className={classes.button}>
                              Reset
                          </Button>
                      </div>
                  ) : (
                      <div>
                          <Typography className={classes.instructions}>{this.getStepContent(activeStep)}</Typography>
                          <div className={classes.formHolder}>
                              {activeStep === 0 && (
                                  <div><Workflow classes={classes} updateWorkflow={this.updateWorkflow} currentWorkflow={this.state.currentWorkflow} /></div>
                              )}
                              {activeStep === 1 && (
                                  <PunchList classes={classes} currentWorkflow={this.state.currentWorkflow} />
                              )}
                              {activeStep === 2 && (
                                <TaskLabeling classes={classes} addLabelsToTask={this.labelTask} labels={this.state.tags} />
                              )}
                              {activeStep === 3 && (
                                <div>Review and Save</div>
                              )}
                          </div>
                          <div className={classes.stepButtons}>
                          <Button disabled={activeStep === 0} onClick={this.handleBack} className={classes.button}>
                              Back
                          </Button>
                          <Button
                              variant="contained"
                              color="primary"
                              onClick={activeStep === steps.length - 1 ? this.buildTask : this.handleNext}
                              className={classes.button}
                          >
                              {activeStep === steps.length - 1 ? 'Finish' : 'Next'}
                          </Button>
                          </div>
                      </div>
                  )}
              </div>
              </PortletContent>
              <PortletFooter className={classes.portletFooter}><PortletLabel title="In progress" /></PortletFooter>            
            </Portlet>
          </form>
        );
      }
}

TaskForm.propTypes = {
    addTask: PropTypes.func.isRequired,
    filterUsers: PropTypes.func.isRequired,
    auth: PropTypes.object.isRequired,
    profile: PropTypes.object.isRequired,
    classes: PropTypes.object.isRequired,
    className: PropTypes.string,
}

const mapStateToProps = state => ({
    auth: state.auth,
    errors: state.errors,
    profile: state.profile
})

export default compose(withStyles(styles), withRouter, connect(mapStateToProps, { addTask, filterUsers })) (TaskForm);