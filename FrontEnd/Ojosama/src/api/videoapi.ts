import axios from "axios";
import { DramaResponse, Drama, DramaEntry } from '../types';

export const getDramas = async (): Promise<DramaResponse[]> => {
    const response = await axios.get("http://localhost:8080/api/dramas");
    return response.data._embedded.dramas;
}

export const deleteDrama = async (link: string): Promise<DramaResponse> => {
    const response = await axios.delete(link);
    return response.data
}

export const addDrama = async (drama: Drama): Promise<DramaResponse> => {
    const response = await axios.post("http://localhost:8080/api/dramas", drama, {
    headers: {
        'Content-Type': 'application/json',
    },  
    });
    
    return response.data;
}

export const updateDrama = async (dramaEntry: DramaEntry):
  Promise<DramaResponse> => {
  const response = await axios.put(dramaEntry.url, dramaEntry.drama, {
    headers: {
      'Content-Type': 'application/json'
    },
  });
  return response.data;
}
