import React, { Component, Fragment } from 'react';
import { withRouter } from 'react-router-dom';

// Externals
import classNames from 'classnames';
import compose from 'recompose/compose';
import PropTypes from 'prop-types';
import { connect } from "react-redux";

import { getTasks } from "../../../../actions/taskActions";
import { logoutUser } from "../../../../actions/authActions";
import { clearCurrentProfile } from "../../../../actions/profileActions";

// Material helpers
import { withStyles } from '@material-ui/core';

import TaskForm from '../../../../views/TaskList/components/TaskForm/TaskForm';

// Material components
import {
  Badge,
  IconButton,
  Popover,
  Toolbar,
  Typography,
  Modal
} from '@material-ui/core';

// Material icons
import {
  Menu as MenuIcon,
  Close as CloseIcon,
  NotificationsOutlined as NotificationsIcon,
  Input as InputIcon,
  NoteAdd as Note
} from '@material-ui/icons';

// Custom components
import { NotificationList } from './components';

// Component styles
import styles from './styles';

class Topbar extends Component {
  signal = true;

  state = {
    notificationsEl: null,
    modalOpen: false,
  };


  componentDidMount() {
    this.signal = true;
    this.props.getTasks();
  }

  componentWillUnmount() {
    this.signal = false;
  }

  handleSignOut = e => {
    e.preventDefault();
    this.props.clearCurrentProfile();
    this.props.logoutUser();

    const { history } = this.props;

    localStorage.setItem('isAuthenticated', false);
    history.push('/login');
  };

  handleShowNotifications = event => {
    this.setState({
      notificationsEl: event.currentTarget
    });
  };

  handleCloseNotifications = () => {
    this.setState({
      notificationsEl: null
    });
  };

  handleOpenModal = () => {
    this.setState({modalOpen: !this.state.modalOpen});
  }

  handleCloseModal = () => {
    this.setState({modalOpen: !this.state.modalOpen});
  }

  render() {
    const {
      classes,
      className,
      title,
      isSidebarOpen,
      onToggleSidebar
    } = this.props;
    const { notifications, notificationsEl, modalOpen } = this.state;

    const rootClassName = classNames(classes.root, className);
    const showNotifications = Boolean(notificationsEl);

    return (
      <Fragment>
        <div className={rootClassName}>
          <Toolbar className={classes.toolbar}>
            <IconButton
              className={classes.menuButton}
              onClick={onToggleSidebar}
              variant="text"
            >
              {isSidebarOpen ? <CloseIcon /> : <MenuIcon />}
            </IconButton>
            <Typography
              className={classes.title}
              variant="h4"
            >
              {title}
            </Typography>
            {/* <IconButton
              className={classes.addWorkflowButton}
              onClick={this.handleOpenModal}
            >
              <Note />
            </IconButton> */}
            <IconButton
              className={classes.addWorkflowButton}
              onClick={this.handleShowNotifications}
            >
              <Badge
                badgeContent={0}
                color="primary"
                variant="dot"
              >
                <NotificationsIcon />
              </Badge>
            </IconButton>
            <IconButton
              className={classes.signOutButton}
              onClick={this.handleSignOut}
            >
              <InputIcon />
            </IconButton>
          </Toolbar>
        </div>
        <Popover
          anchorEl={notificationsEl}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'center'
          }}
          onClose={this.handleCloseNotifications}
          open={showNotifications}
          transformOrigin={{
            vertical: 'top',
            horizontal: 'center'
          }}
        >
          <NotificationList
            notifications={notifications}
            onSelect={this.handleCloseNotifications}
          />
        </Popover>
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={modalOpen}
          onClose={this.handleCloseModal}
          keepMounted={true}
          style={{display:'flex',alignItems:'center',justifyContent:'center'}}
        >
          <div style={{top: '25%', margin: 'auto'}} className={classes.modal}>
            <TaskForm />
          </div>          
        </Modal>
      </Fragment>
    );
  }
}

Topbar.propTypes = {
  className: PropTypes.string,
  classes: PropTypes.object.isRequired,
  history: PropTypes.object.isRequired,
  isSidebarOpen: PropTypes.bool,
  onToggleSidebar: PropTypes.func,
  title: PropTypes.string,
  getTasks: PropTypes.func,
  logoutUser: PropTypes.func.isRequired,
  clearCurrentProfile: PropTypes.func.isRequired
};

Topbar.defaultProps = {
  onToggleSidebar: () => {}
};

const mapStateToProps = state => ({
  auth: state.auth,
  tasks: state.tasks
})

export default compose(
  withRouter,
  withStyles(styles),
  connect(mapStateToProps, { getTasks, logoutUser, clearCurrentProfile })
)(Topbar);