import { useEffect, useState } from 'react';
import axios from "axios";
import React from 'react';
import './Traditional.css';

function Traditional() {
    const [memes, setMemes] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [isError, setIsError] = useState(false);
    

    const fetchMemes = async () => {
        try {
            const response = await axios.get("https://api.imgflip.com/get_memes");
            setMemes(response.data.data.memes);
        } 
        catch (error) {
            console.error("Error fetching memes:", error);
            setIsError(true);
        } 
        finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        setTimeout(() => {
            fetchMemes();
        }, 1000); 
    }, []);

    if (isLoading) {
        return <div className="loading-message">Page is loading...</div>;
    }

    if (isError) {
        return <div className="error-message">Error has occurred...</div>;
    }

    return (
        <div className="memes-container">
            <div className="api-description">
                <h2 className="description-title">About the Meme Generator API</h2>
                <p className="description-text">
                    The Meme Generator API generates memes in a JSON format by choosing a preloaded image
                    and short top and bottom text sentences as input. The API returns a JPEG image file format.
                    It provides a rich array of memes with captioned text customized according to the user's requirements.
                    This API is commonly used to generate memes programmatically for use in applications.
                </p>
            </div>
            <div className="memes-grid">
                {memes.map(meme => (
                    <div className="meme-card">
                        <h3 className="meme-title">{meme.name}</h3>
                        <img src={meme.url} alt={meme.name} className="meme-image" />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Traditional;
