import React, { Component } from 'react'

import PropTypes from 'prop-types';

import {sortableContainer, sortableElement} from 'react-sortable-hoc';
import arrayMove from 'array-move';

import TextField from '@material-ui/core/TextField';

import clsx from 'clsx';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Collapse from '@material-ui/core/Collapse';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Delete from '@material-ui/icons/Delete'
import Button from '@material-ui/core/Button';

import MoreVert from '@material-ui/icons/MoreVert';
import Chip from '@material-ui/core/Chip';

import WorkflowMenu from './components/WorkflowMenu';
import WorkflowAssign from './components/WorkflowAssign';

import DateField from '../date-field/DateField';
import ClassificationField from '../classification-field/ClassificationField';
import TitleField from '../title-field/TitleField';
import DescriptionField from '../description-field/DescriptionField';
import PriorityField from '../priority-field/PriorityField';

class Workflow extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            item: '',
            anchorEl: null,
            isOpen: false,
        };
    }

    componentDidMount = () => {
        const { currentWorkflow } = this.props;
        if(currentWorkflow.length > 0) this.setState({items: currentWorkflow});
    }

    onSortEnd = ({oldIndex, newIndex}) => {
        const { updateWorkflow, currentWorkflow } = this.props;
        this.setState(({items}) => ({
            items: arrayMove(items, oldIndex, newIndex),
        }), updateWorkflow(arrayMove(currentWorkflow, oldIndex, newIndex)));
    };

    onChange = e => {
        this.setState({[e.target.name]: e.target.value});
    }

    onKeyDown = e => {
        const { updateWorkflow } = this.props;
        if(e.keyCode === 13){
            let items = this.state.items;
            let newItem = {
                name: this.state.item,
                title: '',
                classification: '',
                description: '',
                due: new Date(),
                priority: '',
                files: [],
                tag: '',
                approver: null
            }
            items.push(newItem);
            this.setState({items: items, item: ''}, console.log(this.state));
            updateWorkflow(items);
        }
    }

    addWorkflowIdentifier = (identifier, index) => {
        let items = this.state.items;
        items[index].tag = identifier;
        this.setState({ items: items });
    }

    addApprover = (approver, index) => {
        let items = this.state.items;
        items[index].approver = approver;
        this.setState({items: items});
    }

    handleExpandClick = () => {
        this.setState({isOpen: !this.state.isOpen});
    }

    setDueDate = (date, index) => {
        let items = this.state.items;
        items[index].due = date;
        this.setState({items: items});
    }

    setItemClassification = (classification, index) => {
        let items = this.state.items;
        items[index].classification = classification;
        this.setState({items: items});
    }

    changeField = (title, index) => {
        let items = this.state.items;
        items[index].title = title;
        this.setState({items: items});
    }

    addTaskDescription = (description, index) => {
        let items = this.state.items;
        items[index].description = description;
        this.setState({items: items});
    }

    addPriorityToTask = (priority, index) => {
        let items = this.state.items;
        items[index].priority = priority;
        this.setState({items: items});
    }

    handleDelete = chipToDelete => () => {
        let items = this.state.items;
        let item = items.findIndex(item => item.name === chipToDelete.name);
        items[item].tag = '';
        this.setState({items: items});
    }

    saveWorkflow = () => {
        const { updateWorkflow } = this.props;
        const { items } = this.state;

        updateWorkflow(items);
    }

    deleteWorkflowItem = itemToDelete => () => {
        let items = this.state.items.filter(item => item.name !== itemToDelete.name);
        this.setState({items: items});
    }

    render() {
        const { items, item } = this.state;
        const { classes } = this.props;

        const SortableItem = sortableElement(({value, index, key}) => {
            return <li key={key}>
                <Card className={classes.card}>
                    <CardHeader
                        avatar={
                        <Avatar aria-label="recipe" className={classes.avatar}>
                            <WorkflowMenu index={index} classes={classes} addWorkflowIdentifier={this.addWorkflowIdentifier} />
                        </Avatar>
                        }
                        action={
                        <IconButton aria-label="settings" className={classes.iconMove}>
                            <MoreVert />
                        </IconButton>
                        }
                        title={value.name}
                    />
                    
                    {items[index].tag && (
                        <CardContent>
                                <div className={classes.flex}>
                                    <Chip
                                        key={index}
                                        label={items[index].tag}
                                        onDelete={this.handleDelete(items[index])}
                                        className={classes.chip}
                                        color="primary"
                                        name={items[index]}
                                    />
                                </div>
                        </CardContent>
                    )}
                    
                    <CardActions disableSpacing>
                        <IconButton
                            className={clsx(classes.delete)}
                            onClick={this.deleteWorkflowItem(items[index])}
                            aria-expanded={this.state.isOpen}
                            aria-label="delete"
                        >
                            <Delete />
                        </IconButton>
                        <IconButton
                            className={clsx(classes.expand, {
                                [classes.expandOpen]: this.state.isOpen,
                            })}
                            onClick={this.handleExpandClick}
                            aria-expanded={this.state.isOpen}
                            aria-label="show more"
                        >
                        <ExpandMoreIcon />
                        </IconButton>
                    </CardActions>
                    <Collapse in={this.state.isOpen} timeout="auto" unmountOnExit>
                        <CardContent>
                            <ClassificationField 
                                classes={classes} 
                                classification={items[index].classification} 
                                setItemClassification={this.setItemClassification} 
                                index={index} 
                            />
                            <TitleField 
                                classes={classes}
                                changeField={this.changeField}
                                index={index}
                                title={items[index].title}
                            />
                            <DescriptionField 
                                classes={classes}
                                addTaskDescription={this.addTaskDescription}
                                index={index}
                                description={items[index].description}
                            />
                            <WorkflowAssign 
                                classes={classes}
                                addApprover={this.addApprover}
                                index={index}
                                approver={items[index].approver}
                            />
                            <PriorityField 
                                classes={classes}
                                addPriorityToTask={this.addPriorityToTask}
                                index={index}
                                priority={items[index].priority}
                            />
                            <DateField 
                                classes={classes} 
                                due={items[index].due} 
                                setDueDate={this.setDueDate} 
                                index={index}
                            />
                        </CardContent>
                    </Collapse>
                    </Card>
            </li>
        });

        const SortableContainer = sortableContainer(({children}) => {
            return <ul className={classes.list}>{children}</ul>;
        });

        return (
            <div className={classes.field}>
                <div className={classes.center}>
                    <TextField
                        id="standard-name"
                        label="Workflow Step"
                        className={classes.input}
                        value={item}
                        onChange={this.onChange}
                        onKeyDown={this.onKeyDown}
                        name="item"
                        margin="normal"
                    />
                </div>
                <SortableContainer onSortEnd={this.onSortEnd} pressDelay={200}>
                    {items.map((value, index) => (
                        <SortableItem key={`item-${index}`} index={index} value={value}  />
                    ))}
                </SortableContainer>
                <div className={classes.marginYCentered}>
                    {items.length > 0 && (
                        <Button color="primary" variant="contained" onClick={this.saveWorkflow}>
                            Finalize Workflow
                        </Button>
                    )}
                </div>
            </div>
        )
    }
}

Workflow.propTypes = {
    classes: PropTypes.object,
    currentWorkflow: PropTypes.array,
    updateWorkflow: PropTypes.func
}

export default Workflow;