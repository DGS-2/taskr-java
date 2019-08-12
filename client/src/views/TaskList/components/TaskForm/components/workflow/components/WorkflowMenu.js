import React, { Component } from 'react'

import PropTypes from 'prop-types';

import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import MenuIcon from '@material-ui/icons/Menu';
import IconButton from '@material-ui/core/IconButton';

class WorkflowMenu extends Component {
    constructor(props) {
        super(props);
        this.state = {
            anchorEl: null,
            isOpen: false
        };
    }

    handleClick = event => {
        this.setState({
            isOpen: !this.state.isOpen,
            anchorEl: event.currentTarget
        });
    }

    chooseAction = e => {
        const { addWorkflowIdentifier } = this.props;
        let index = e.currentTarget.value;
        let identifier = e.currentTarget.textContent;
        addWorkflowIdentifier(identifier, index);
    }

    handleClose = e => {
        
        this.setState({ isOpen: !this.state.isOpen });
    }

    render() {
        const { index, classes } = this.props;
        return (
            <div>
                <IconButton className={classes.iconButton} aria-controls={"simple-menu-" + index} aria-haspopup="true" onClick={this.handleClick} >
                    <MenuIcon />
                </IconButton>
                <Menu
                    id={"simple-menu-" + index}
                    anchorEl={this.state.anchorEl}
                    keepMounted
                    open={this.state.isOpen}
                    onClose={this.handleClose}
                >
                    <MenuItem value={index} onClick={this.chooseAction}>Approve</MenuItem>
                    <MenuItem value={index} onClick={this.chooseAction}>Corrections Needed</MenuItem>
                    <MenuItem value={index} onClick={this.chooseAction}>Review</MenuItem>
                    <MenuItem value={index} onClick={this.chooseAction}>Sign</MenuItem>
                    <MenuItem value={index} onClick={this.chooseAction}>Review and Sign</MenuItem>
                </Menu>
            </div>
        )
    }
}

WorkflowMenu.propTypes = {
    index: PropTypes.number,
    classes: PropTypes.object,
    addWorkflowIdentifier: PropTypes.func
}

export default WorkflowMenu;