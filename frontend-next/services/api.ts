import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor opcional para inyectar el token JWT en peticiones protegidas (lo usaremos en la Fase 4)
api.interceptors.request.use(
  (config) => {
    // Aquí recuperaremos el token de localStorage o cookies más adelante
    const token = typeof window !== 'undefined' ? localStorage.getItem('jwt_token') : null;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default api;