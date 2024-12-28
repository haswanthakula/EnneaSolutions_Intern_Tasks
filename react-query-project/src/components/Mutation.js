import React, { useState } from 'react';
import { useQuery, useMutation } from '@tanstack/react-query';
import axios from 'axios';
import { useQueryClient } from '@tanstack/react-query';
import './Mutation.css';

const fetchPosts = () => {
    return axios.get("http://localhost:4000/posts");
};

const addPost = (post) => {
    return axios.post("http://localhost:4000/posts", post);
};


const deletePost = (postId) => {
    return axios.delete(`http://localhost:4000/posts/${postId}`);
};

const Mutation = () => {
    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");
    const queryClient = useQueryClient();

    const { data, isLoading, isError, error } = useQuery({
        queryKey: ["posts"],
        queryFn: fetchPosts,
        
    });

    const { mutate } = useMutation({
        mutationFn: addPost,
        onSuccess: () => {
            queryClient.invalidateQueries("posts");
        }
    });


    const { mutate: deletePostMutate } = useMutation({
        mutationFn: deletePost,
        onSuccess: () => {
            queryClient.invalidateQueries('posts');
        },
    });


    const handleSubmit = (e) => {
        e.preventDefault();

        if (!title.trim() || !body.trim()) {
            alert("Both title and body are required!");
            return;
        }

        const post = { title, body };
        mutate(post);
        setTitle("");
        setBody("");
    };

    const handleDelete = (postId) => {
        deletePostMutate(postId);
    };



    if (isLoading) {
        return <div className="loading-message">Page is loading...</div>;
    }

    if (isError) {
        return <div className="error-message">{error.message}</div>;
    }

    return (
        <div className="container">
            <div className="description">
                <h2>About the Post Adding Functionality</h2>
                <p>
                    This application uses <strong>JSON Server</strong> as a mock REST API for managing data.
                    The server runs on port <strong>4000</strong> and serves data from the `db.json` file.
                    Each post contains an <strong>id</strong>, <strong>title</strong>, and <strong>body</strong>.
                    You can view the posts and add new posts using the form below.
                </p>
                <p>
                    JSON Server is a simple and lightweight tool for testing and prototyping APIs. 
                    It allows developers to quickly create a full fake REST API without setting up a 
                    backend server.
                </p>
            </div>
            <form onSubmit={handleSubmit}>
                <input
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="Enter post title"
                    value={title}
                />
                <input
                    onChange={(e) => setBody(e.target.value)}
                    placeholder="Enter post body"
                    value={body}
                />
                <button type="submit">Add Post</button>
            </form>
            <div className="posts-list">
                {data?.data.map(post => (
                        <div className="post">
                            <h3>{post.title}</h3>
                            <p>{post.body}</p> <br/>
                            <button onClick={() => handleDelete(post.id)}>Delete</button>
                        </div>
                ))}
            </div>
        </div>
    );
};

export default Mutation;