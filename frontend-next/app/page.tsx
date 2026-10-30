import { onboardingService } from '@/services/onboardingService';
import Link from 'next/link';
import { BookOpen, PlusCircle, ShieldCheck, Server } from 'lucide-react';
import { Suspense } from 'react';
import { OnboardingResourceDto } from '@/types/onboarding';

// Forzamos comportamiento dinámico

// Componente interno que realiza el fetch de datos
async function ResourcesGrid() {
  let resources: OnboardingResourceDto[] = [];
  let errorMessage = null;

  try {
    resources = await onboardingService.getAllResources();
  } catch (error) {
    errorMessage = 'No se pudo conectar con el backend de Spring Boot en http://localhost:8080';
  }

  if (errorMessage) {
    return (
      <div className="bg-rose-950/50 border border-rose-800/80 text-rose-200 px-5 py-4 rounded-xl text-sm flex items-center gap-3">
        <span className="font-bold">⚠️ Error de Conexión:</span> {errorMessage}
      </div>
    );
  }

  if (resources.length === 0) {
    return (
      <div className="text-center py-16 bg-gray-900/50 border border-gray-800 rounded-2xl">
        <p className="text-gray-400 text-sm">No hay recursos de onboarding registrados en la base de datos.</p>
        <Link
          href="/onboarding/new"
          className="inline-block mt-4 text-xs font-semibold text-indigo-400 hover:text-indigo-300 underline underline-offset-4"
        >
          Registra el primero ahora →
        </Link>
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      {resources.map((item: OnboardingResourceDto) => (
        <div
          key={item.id}
          className="bg-gray-900/80 border border-gray-800/80 rounded-2xl p-6 flex flex-col justify-between hover:border-indigo-500/50 transition-all group shadow-xl"
        >
          <div className="space-y-3">
            <div className="flex justify-between items-start">
              <span className="text-xs font-semibold px-2.5 py-1 bg-indigo-950 text-indigo-300 border border-indigo-800/50 rounded-lg">
                {item.category || 'General'}
              </span>
              <span className="text-xs text-gray-500 font-mono">ID: #{item.id}</span>
            </div>

            <h3 className="text-lg font-bold text-white group-hover:text-indigo-300 transition-colors">
              {item.title}
            </h3>

            <p className="text-sm text-gray-400 line-clamp-3">
              {item.description}
            </p>
          </div>

          <div className="mt-6 pt-4 border-t border-gray-800/80 flex items-center justify-between text-xs text-gray-400">
            <span>Por: <strong className="text-gray-300">{item.author}</strong></span>
            <Link
              href={`/onboarding/${item.id}`}
              className="text-indigo-400 font-semibold hover:underline"
            >
              Ver detalle →
            </Link>
          </div>
        </div>
      ))}
    </div>
  );
}

// Componente Principal de la Página
export default function Home() {
  return (
    <main className="min-h-screen bg-gray-950 text-gray-100 p-6 md:p-12">
      <div className="max-w-6xl mx-auto space-y-8">
        
        {/* Header Corporativo */}
        <header className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4 border-b border-gray-800 pb-6">
          <div>
            <div className="flex items-center gap-2 text-indigo-400 text-sm font-semibold tracking-wider uppercase mb-1">
              <Server className="w-4 h-4" /> Enterprise Task & Resource Hub
            </div>
            <h1 className="text-3xl font-extrabold tracking-tight text-white">
              Catálogo de Onboarding & Guías Técnicas
            </h1>
            <p className="text-gray-400 text-sm mt-1">
              Arquitectura enterprise con Java 25, Virtual Threads y Next.js App Router.
            </p>
          </div>

          <div className="flex items-center gap-3">
            <span className="hidden sm:flex items-center gap-1.5 text-xs bg-emerald-950/80 text-emerald-400 border border-emerald-800 px-3 py-1.5 rounded-full font-medium">
              <ShieldCheck className="w-3.5 h-3.5" /> OIDC / JWT Ready
            </span>
            <Link
              href="/onboarding/new"
              className="inline-flex items-center gap-2 bg-indigo-600 hover:bg-indigo-500 text-white text-sm font-semibold px-4 py-2.5 rounded-xl transition-all shadow-lg shadow-indigo-600/20"
            >
              <PlusCircle className="w-4 h-4" /> Nuevo Recurso
            </Link>
          </div>
        </header>

        {/* Sección de Contenido con Suspense */}
        <section>
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-bold text-white flex items-center gap-2">
              <BookOpen className="w-5 h-5 text-indigo-400" /> Guías Disponibles
            </h2>
          </div>

          <Suspense fallback={<div className="text-gray-400 text-sm py-12 text-center animate-pulse">Cargando recursos desde el backend...</div>}>
            <ResourcesGrid />
          </Suspense>
        </section>

      </div>
    </main>
  );
}