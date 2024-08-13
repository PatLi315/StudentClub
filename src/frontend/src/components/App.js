import React from 'react';
import './App.css';
import ClubList from './ClubList';
import EventList from './EventList';

function App() {
    return (
        <div className="App">
            <h1>Student Club Event Management</h1>
            <ClubList />
            <EventList />
        </div>
    );
}

export default App;
