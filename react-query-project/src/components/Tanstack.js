import React from 'react';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import './Tanstack.css';

const Tanstack = () => {
    const { data, isLoading, isError, error} = useQuery({
        queryKey: ['todo'],
        queryFn: () => {
            return axios.get('https://jsonplaceholder.typicode.com/posts');
        },
    });

    if (isLoading) {
        return <div className="loading">Page is loading...</div>;
    }

    if (isError) {
        return <div className="error">{error.message}</div>;
    }

    return (
        <div className="tanstack-container">
            
            <h1>Posts</h1>

            <p className="api-description">
                JSONPlaceholder is a free online REST API that you can use whenever you need some fake data for testing and development purposes. It provides a variety of data types, including posts, comments, users, and more. In this example, we are fetching 200 posts from the API, each containing a title and body. This is a great resource for front-end developers who want to simulate API responses without relying on a live backend.
            </p>

            <div className="tanstack-grid">
                {data.data.map((todo) => (
                    <div className="tanstack-article">
                        <h3>{todo.title}</h3>
                        <p>{todo.body}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Tanstack;
