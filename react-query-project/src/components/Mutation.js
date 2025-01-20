import React, { useState } from 'react';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import './Mutation.css';

const API_URL = 'https://mutation-posts-json.onrender.com/posts';

const fetchPosts = async () => {
  const { data } = await axios.get(API_URL);
  return data;
};

const addPost = async (newPost) => {
  const { data } = await axios.post(API_URL, newPost);
  return data;
};

const deletePost = async (postId) => {
  await axios.delete(`${API_URL}/${postId}`);
  return postId;
};

function Mutation() {
  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');
  const [error, setError] = useState('');
  const queryClient = useQueryClient();

  const { data: posts, isLoading, isError } = useQuery({
    queryKey: ['posts'],
    queryFn: fetchPosts
  });

  const addPostMutation = useMutation({
    mutationFn: addPost,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['posts'] });
      setTitle('');
      setBody('');
      setError('');
    }
  });

  const deletePostMutation = useMutation({
    mutationFn: deletePost,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['posts'] });
    }
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Trim and validate title and body
    const trimmedTitle = title.trim();
    const trimmedBody = body.trim();

    // Check for empty or only-space inputs
    if (!trimmedTitle) {
      setError('Title cannot be empty or contain only spaces');
      return;
    }

    if (!trimmedBody) {
      setError('Body cannot be empty or contain only spaces');
      return;
    }

    // Clear any previous errors
    setError('');

    // Add post with trimmed values
    addPostMutation.mutate({ 
      title: trimmedTitle, 
      body: trimmedBody 
    });
  };

  if (isLoading) return <div>Loading...</div>;
  if (isError) return <div>Error fetching posts</div>;

  return (
    <div className="mutation-container">
      <h1>Post Management</h1>
      
      {error && <div className="error-message">{error}</div>}
      
      <form onSubmit={handleSubmit} className="post-form">
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <textarea
          placeholder="Body"
          value={body}
          onChange={(e) => setBody(e.target.value)}
          required
        />
        <button type="submit" disabled={addPostMutation.isLoading}>
          {addPostMutation.isLoading ? 'Adding...' : 'Add Post'}
        </button>
      </form>

      <div className="posts-list">
        <h2>Existing Posts</h2>
        {posts.map((post) => (
          <div key={post.id} className="post-item">
            <div className="post-content">
              <h3>{post.title}</h3>
              <p>{post.body}</p>
            </div>
            <button 
              onClick={() => deletePostMutation.mutate(post.id)}
              disabled={deletePostMutation.isLoading}
              className="delete-btn"
            >
              {deletePostMutation.isLoading ? 'Deleting...' : 'Delete'}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Mutation;