import { OnboardingResourceDto } from '@/types/onboarding';

const API_BASE_URL = 'http://localhost:8080/api/v1';

export const onboardingService = {
  // GET: Obtener todos los recursos
  async getAllResources(): Promise<OnboardingResourceDto[]> {
    const response = await fetch(`${API_BASE_URL}/onboarding`, {
      cache: 'no-store', // Fuerza a que sea dinámico y evita el error de prerendering
    });

    if (!response.ok) {
      throw new Error('Error al obtener los recursos de onboarding');
    }

    return response.json();
  },

  // GET: Obtener recurso por ID
  async getResourceById(id: number): Promise<OnboardingResourceDto> {
    const response = await fetch(`${API_BASE_URL}/onboarding/${id}`, {
      cache: 'no-store',
    });

    if (!response.ok) {
      throw new Error(`Recurso con ID ${id} no encontrado`);
    }

    return response.json();
  },

  // POST: Crear nuevo recurso (Preparado para JWT en la Fase 4)
  async createResource(dto: OnboardingResourceDto, token?: string): Promise<OnboardingResourceDto> {
    const headers: HeadersInit = {
      'Content-Type': 'application/json',
    };

    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }

    const response = await fetch(`${API_BASE_URL}/onboarding`, {
      method: 'POST',
      headers,
      body: JSON.stringify(dto),
    });

    if (!response.ok) {
      throw new Error('Error al crear el recurso de onboarding');
    }

    return response.json();
  },
};