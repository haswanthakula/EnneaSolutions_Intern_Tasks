import React from 'react';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import './Tanstack.css';

const Tanstack = () => {
    const { data, isLoading, isError, error} = useQuery({
        queryKey: ['cartoons'],
        queryFn: () => {
            return axios.get('https://rickandmortyapi.com/api/character');
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
            <h1>Rick and Morty Characters</h1>

            <p className="api-description">
                Dive into the multiverse with characters from the hit animated series Rick and Morty! 
                Explore a diverse collection of unique and bizarre characters from across different dimensions.
            </p>

            <div className="tanstack-grid">
                {data.data.results.map((character) => (
                    <div key={character.id} className="tanstack-article">
                        <img 
                            src={character.image} 
                            alt={character.name} 
                            style={{width: '100%', height: '250px', objectFit: 'cover'}}
                        />
                        <h3>{character.name}</h3>
                        <div className="character-details">
                            <p>Status: {character.status}</p>
                            <p>Species: {character.species}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Tanstack;
