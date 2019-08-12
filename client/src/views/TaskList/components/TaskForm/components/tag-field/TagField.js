import React, { Component } from 'react'
import PropTypes from 'prop-types';

import { WithContext as ReactTags } from 'react-tag-input';

import { delimiters } from "../../../../../../const/consts";

class TagField extends Component {
    // filterUsers = users => {
    //   let arr = [];
    //   users.forEach(user => {
    //     let item = {id: user.user._id, text: `${user.rank.abreviated} ${user.user.name}`};
    //     arr.push(item);
    //   });

    //   return arr;
    // }

    render() {
        const { classes, profile, handleAddition, handleDelete, taggableUsers } = this.props;
        // const suggestions = this.filterUsers(profile.profiles);

        return (
            <div className={classes.field}>                   
                <ReactTags 
                    name="to"
                    placeholder="Start typing to assign task to members"              
                    inputFieldPosition="inline"
                    tags={taggableUsers}
                    suggestions={suggestions}
                    handleDelete={handleDelete}
                    handleAddition={handleAddition}
                    delimiters={delimiters}
                    autocomplete={true}
                    autofocus={false}
                    minQueryLength={1}
                    classNames={{
                        tags: classes.ReactTags__tags,
                        tagInput: classes.ReactTags__tagInput,
                        tagInputField: classes.ReactTags__tagInputField,
                        selected: classes.ReactTags__selected,
                        tag: classes.ReactTags__selected,
                        remove: classes.ReactTags__selected,
                        suggestions: classes.ReactTags__suggestions
                    }}
                />                    
            </div>
        )
    }
}


TagField.propTypes = {
    classes: PropTypes.object,
    handleAddition: PropTypes.func,
    handleDelete: PropTypes.func,
    profile: PropTypes.object,
    taggableUsers: PropTypes.array
}

export default TagField;