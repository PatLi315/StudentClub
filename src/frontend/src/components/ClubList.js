import React from 'react';

function ClubList() {
    const clubs = ["CISSA", "Robotics Club"];

    return (
        <div>
            <h2>Clubs</h2>
            <ul>
                {clubs.map((club, index) => (
                    <li key={index}>{club}</li>
                ))}
            </ul>
        </div>
    );
}

export default ClubList;
