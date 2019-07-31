import React, { Component } from 'react';
import moment from "moment";
import compose from "recompose/compose";
import { connect } from "react-redux";
// Externals
import PropTypes from 'prop-types';
import classNames from 'classnames';

// Material helpers
import { withStyles } from '@material-ui/core';

// Material components
import { Typography, Divider, InputBase, IconButton, Grid } from '@material-ui/core';

// Material icons
import {
  AccessTime as AccessTimeIcon,
  Share as GetAppIcon,
  Send
} from '@material-ui/icons';

// Actions
import { replyToMessageThread } from '../../../../actions/taskActions';

// Shared components
import { Paper } from '../../../../components';

// Component styles
import styles from './styles';
class TaskCard extends Component {
  state = {
    threadReply: ''
  }

  onChange = e => {
    this.setState({[e.target.name]: e.target.value});
  }

  reply = () => {
    const { task } = this.props;
    let id = task._id;
    let message = {
      message: this.state.threadReply,
      time: Date.now()
    };

    // Protect against blank submissions
    if(this.state.threadReply !== ''){
      this.props.replyToMessageThread(id,message);
    }
  }

  render() {
    const { classes, className, task } = this.props;
    
    const rootClassName = classNames(classes.root, className);

    return (
      <Paper className={rootClassName}>
        <div className={classes.details}>
          <Typography
            className={classes.title}
            variant="h2"
          >
            {task.classification.classification}
          </Typography>
        </div>
        <div className={classes.details}>
          <Typography
            className={classes.title}
            variant="h4"
          >
            {task.title}
          </Typography>
          <Typography
            className={classes.description}
            variant="body1"
          >
            {task.description}
          </Typography>
        </div>
        
        <Divider />
        <div className={classes.chat}>
          <Paper className={classes.replyInput}>
            <InputBase 
              className={classes.replyText}
              placeholder="Reply to thread"
              name="threadReply"
              value={this.state.threadReply}
              onChange={this.onChange}
              multiline
            />
            <IconButton className={classes.replySend} onClick={this.reply}>
                <Send />
            </IconButton>
          </Paper>
          <Grid
            container
            direction="row"                  
          >
            {task.message_thread && (
              task.message_thread.messages.map((message,i) => {
                return  <Grid 
                    item 
                    key={i} 
                    xs={12}
                  >
                    <div className={message.from === task.created_by ? classes.myMessage : classes.fromMessage}>{message.message}</div>                    
                  </Grid>
                
              })
            )}  
            </Grid>     
        </div>        
        <Divider />
        <div className={classes.stats}>
          <AccessTimeIcon className={classes.updateIcon} />
          <Typography
            className={classes.updateText}
            variant="body2"
          >
            Created on {moment(task.created_on).format('MMM DD YYYY')}
          </Typography>
          <Typography
            className={classes.updateText}
            variant="body2"
          >
            <strong>
              Due by {moment(task.due_by).format('MMM DD YYYY')}
            </strong>            
          </Typography>
          <GetAppIcon className={classes.downloadsIcon} />
          <Typography
            className={classes.downloadsText}
            variant="body2"
          >
            share
          </Typography>
        </div>
      </Paper>
    );
  }
}

TaskCard.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired,
  task: PropTypes.object.isRequired,
  profile: PropTypes.object,
  replyToMessageThread: PropTypes.func
};

const mapStateToProps = state => ({
  profile: state.profile
});

export default compose(withStyles(styles), connect(mapStateToProps, {replyToMessageThread}) )(TaskCard);
