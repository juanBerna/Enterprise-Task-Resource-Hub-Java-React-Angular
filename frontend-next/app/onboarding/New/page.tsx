'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { ArrowLeft, Send, ShieldAlert, CheckCircle2 } from 'lucide-react';
import { onboardingService } from '@/services/onboardingService';

export default function NewOnboardingPage() {
  const router = useRouter();
  
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    category: '',
    author: '',
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      // Llamamos al servicio de creación (por ahora sin token estricto o con el que esté en localStorage)
      const token = typeof window !== 'undefined' ? localStorage.getItem('jwt_token') || undefined : undefined;
      
      await onboardingService.createResource(formData, token);
      
      setSuccess(true);
      setTimeout(() => {
        router.push('/');
        router.refresh();
      }, 1500);
    } catch (error) {
      setError('Error al registrar el recurso en el backend. Verifica que los campos sean válidos o que tengas autorización.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-gray-950 text-gray-100 p-6 md:p-12 flex items-center justify-center">
      <div className="max-w-2xl w-full bg-gray-900/80 border border-gray-800 rounded-2xl p-8 shadow-2xl space-y-6">
        
        {/* Navegación de Retorno */}
        <div>
          <Link
            href="/"
            className="inline-flex items-center gap-2 text-xs text-indigo-400 hover:text-indigo-300 font-semibold mb-4 transition-colors"
          >
            <ArrowLeft className="w-4 h-4" /> Volver al Catálogo
          </Link>
          <h1 className="text-2xl font-extrabold tracking-tight text-white">
            Registrar Nuevo Recurso de Onboarding
          </h1>
          <p className="text-gray-400 text-sm mt-1">
            Crea una nueva guía técnica o estándar de arquitectura para el equipo.
          </p>
        </div>

        {/* Alertas de Éxito / Error */}
        {error && (
          <div className="bg-rose-950/50 border border-rose-800 text-rose-200 px-4 py-3 rounded-xl text-sm flex items-center gap-3">
            <ShieldAlert className="w-5 h-5 text-rose-400 flex-shrink-0" />
            <span>{error}</span>
          </div>
        )}

        {success && (
          <div className="bg-emerald-950/50 border border-emerald-800 text-emerald-200 px-4 py-3 rounded-xl text-sm flex items-center gap-3 animate-fade-in">
            <CheckCircle2 className="w-5 h-5 text-emerald-400 flex-shrink-0" />
            <span>¡Recurso creado con éxito! Redirigiendo al catálogo...</span>
          </div>
        )}

        {/* Formulario */}
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold uppercase tracking-wider text-gray-300 mb-1">
              Título del Recurso
            </label>
            <input
              type="text"
              name="title"
              required
              value={formData.title}
              onChange={handleChange}
              placeholder="Ej. Arquitectura Micro Frontends con Module Federation"
              className="w-full bg-gray-950 border border-gray-800 rounded-xl px-4 py-3 text-sm text-white placeholder-gray-600 focus:outline-none focus:border-indigo-500 transition-colors"
            />
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-semibold uppercase tracking-wider text-gray-300 mb-1">
                Categoría
              </label>
              <select
                name="category"
                required
                value={formData.category}
                onChange={handleChange}
                className="w-full bg-gray-950 border border-gray-800 rounded-xl px-4 py-3 text-sm text-white focus:outline-none focus:border-indigo-500 transition-colors"
              >
                <option value="">Selecciona una categoría</option>
                <option value="Backend">Backend</option>
                <option value="Frontend">Frontend</option>
                <option value="Arquitectura">Arquitectura</option>
                <option value="DevOps">DevOps</option>
              </select>
            </div>

            <div>
              <label className="block text-xs font-semibold uppercase tracking-wider text-gray-300 mb-1">
                Autor / Tech Lead
              </label>
              <input
                type="text"
                name="author"
                required
                value={formData.author}
                onChange={handleChange}
                placeholder="Ej. Juan Carlos Bernabe"
                className="w-full bg-gray-950 border border-gray-800 rounded-xl px-4 py-3 text-sm text-white placeholder-gray-600 focus:outline-none focus:border-indigo-500 transition-colors"
              />
            </div>
          </div>

          <div>
            <label className="block text-xs font-semibold uppercase tracking-wider text-gray-300 mb-1">
              Descripción Detallada
            </label>
            <textarea
              name="description"
              required
              rows={4}
              value={formData.description}
              onChange={handleChange}
              placeholder="Describe los objetivos, tecnologías y pautas de la guía..."
              className="w-full bg-gray-950 border border-gray-800 rounded-xl px-4 py-3 text-sm text-white placeholder-gray-600 focus:outline-none focus:border-indigo-500 transition-colors resize-none"
            />
          </div>

          <div className="pt-4 flex items-center justify-end gap-3">
            <Link
              href="/"
              className="px-5 py-2.5 rounded-xl text-sm font-semibold text-gray-400 hover:text-white transition-colors"
            >
              Cancelar
            </Link>
            <button
              type="submit"
              disabled={loading || success}
              className="inline-flex items-center gap-2 bg-indigo-600 hover:bg-indigo-500 disabled:bg-indigo-900 text-white text-sm font-semibold px-6 py-2.5 rounded-xl transition-all shadow-lg shadow-indigo-600/20"
            >
              <Send className="w-4 h-4" />
              {loading ? 'Guardando...' : 'Guardar y Persistir'}
            </button>
          </div>
        </form>

      </div>
    </main>
  );
}