import axios from 'axios';
import setAuthToken from '../utils/setAuthToken';
import jwt_decode from 'jwt-decode'; 

import { GET_ERRORS, SET_CURRENT_USER, SET_REGISTERED_USER, CHANGE_PASSWORD } from './types';

import { getCurrentProfile, getProfiles } from "./profileActions";

// Register User
export const registerUser = (userData) => dispatch => {
  axios
    .post('/users/register', userData)
    .then(res => res.data)
    .catch(err => {
        dispatch({
          type: GET_ERRORS,
          payload: err.response.data
        })
      }
    );
};

// Admin Registering User
export const adminRegisterUser = (userData) => dispatch => {
  axios
    .post('/users/register', userData)
    .then(res => {
      dispatch({
        type: SET_REGISTERED_USER,
        payload: res.data
      })
    })
    .catch(err =>
      dispatch({
        type: GET_ERRORS,
        payload: err
      })
    );
};

// Reset Password
export const resetPassword = (data, history) => dispatch => {
  axios.post('/users/reset-password', data)
  .then(res => {
    dispatch({
      type: CHANGE_PASSWORD,
      payload: res.data
    })
    history.push('/dashboard')
  })
  .catch(err => 
    dispatch({
      type: GET_ERRORS,
      payload: err
    })  
  );
}

// Login - Get User Token
export const loginUser = userData => dispatch => {
  axios
    .post('/users/login', userData)
    .then(res => {
      // Save to localStorage
      const { token } = res.data;
      // Set token to ls
      localStorage.setItem('jwtToken', token);
      // Set token to Auth header
      setAuthToken(token);
      // Decode token to get user data
      const decoded = jwt_decode(token);
      // Set current user
      dispatch(setCurrentUser(decoded));
      dispatch(getCurrentProfile())
      dispatch(getProfiles())
      
    })
    .catch(err =>
      dispatch({
        type: GET_ERRORS,
        payload: err.response.data
      })
    );
};

// Set logged in user
export const setCurrentUser = decoded => {
  return {
    type: SET_CURRENT_USER,
    payload: decoded
  };
};

// Log user out
export const logoutUser = () => dispatch => {
  // Remove token from localStorage
  localStorage.removeItem('jwtToken');
  // Remove auth header for future requests
  setAuthToken(false);
  // Set current user to {} which will set isAuthenticated to false
  dispatch(setCurrentUser({}));
};