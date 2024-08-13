import React from 'react';

function EventList() {
    const events = [
        { title: "Welcome Party", description: "Join us for a fun welcome party!" },
        { title: "Tech Talk", description: "A talk on the latest in tech." }
    ];

    return (
        <div>
            <h2>Events</h2>
            <ul>
                {events.map((event, index) => (
                    <li key={index}>
                        <strong>{event.title}</strong>: {event.description}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default EventList;
