"use client";

import { useRouter } from "next/navigation";
import { FaLeaf, FaBell, FaChartLine } from "react-icons/fa";
import Link from "next/link";

export default function HomePage() {
  const router = useRouter();

  return (
    <div className="h-screen w-screen">
      <div className="flex h-full">
        <div className="w-1/2 h-full flex flex-col items-center justify-center bg-slate-900">
          <h1 className="text-7xl font-bold mb-12">
            <span className="text-8xl">
              Aut
              <svg
                viewBox="0 0 512 512"
                className="w-16 h-16 text-red-500 inline-block"
              >
                <path
                  fill="currentColor"
                  d="M256 0C114.6 0 0 114.6 0 256s114.6 256 256 256s256-114.6 256-256S397.4 0 256 0zM256 64c105.9 0 192 86.13 192 192s-86.13 192-192 192S64 361.9 64 256S150.1 64 256 64zM256 96c-88.22 0-160 71.78-160 160s71.78 160 160 160s160-71.78 160-160S344.2 96 256 96zM256 128c70.69 0 128 57.31 128 128c0 70.69-57.31 128-128 128S128 326.7 128 256C128 185.3 185.3 128 256 128zM256 160c-52.94 0-96 43.06-96 96s43.06 96 96 96s96-43.06 96-96S308.9 160 256 160zM256 192c35.28 0 64 28.72 64 64s-28.72 64-64 64s-64-28.72-64-64S220.7 192 256 192z"
                />
              </svg>
            </span>
            <br />
            <span className="text-red-500 mt-2 inline-block">Alchemist</span>
          </h1>
          <div className="w-full max-w-sm">
            <div className="flex space-x-4">
              <Link
                href="/addNewService"
                className="flex-1 px-6 py-3 backdrop-blur-sm bg-red-500 text-white rounded hover:bg-red-600 transition shadow flex items-center"
              >
                <svg
                  className="w-5 h-5 mr-2"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                >
                  <path
                    fillRule="evenodd"
                    d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z"
                    clipRule="evenodd"
                  />
                </svg>
                New Service
              </Link>
              <Link
                href="/serviceHistory"
                className="flex-1 px-6 py-3 backdrop-blur-sm bg-gray-100 text-slate-900 rounded hover:bg-white/80 transition shadow flex items-center"
              >
                <svg
                  className="w-5 h-5 mr-2"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                >
                  <path
                    fillRule="evenodd"
                    d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                    clipRule="evenodd"
                  />
                </svg>
                Service History
              </Link>
            </div>
          </div>
        </div>

        <div className="w-1/2 bg-cover bg-center relative bg-[url(/background.jpg)]">
          <div className="flex flex-col h-full justify-end p-8">
            <div className="backdrop-blur-sm bg-white/70 p-4 rounded-lg shadow flex items-center mb-1">
              <div className="mr-4 bg-green-500 p-3 rounded-full text-white">
                <FaLeaf size={24} />
              </div>
              <div>
                <h3 className="text-lg font-semibold text-slate-900">
                  Environmentally Friendly
                </h3>
                <p className="text-sm text-gray-700">
                  Optimize maintenance schedules to reduce waste and improve
                  fuel efficiency.
                </p>
              </div>
            </div>
            <div className="backdrop-blur-sm bg-white/70 p-4 rounded-lg shadow flex items-center mb-1">
              <div className="mr-4 bg-blue-500 p-3 rounded-full text-white">
                <FaBell size={24} />
              </div>
              <div>
                <h3 className="text-lg font-semibold text-slate-900">
                  Smart Reminders
                </h3>
                <p className="text-sm text-gray-700">
                  Never miss important maintenance with customized alerts based
                  on your driving habits.
                </p>
              </div>
            </div>
            <div className="backdrop-blur-sm bg-white/70 p-4 rounded-lg shadow flex items-center mb-1">
              <div className="mr-4 bg-purple-500 p-3 rounded-full text-white">
                <FaChartLine size={24} />
              </div>
              <div>
                <h3 className="text-lg font-semibold text-slate-900">
                  Performance Analytics
                </h3>
                <p className="text-sm text-gray-700">
                  Track vehicle performance metrics and maintenance costs over
                  time.
                </p>
              </div>
            </div>
            <div className="backdrop-blur-sm bg-white/70 p-6 rounded-lg shadow">
              <h2 className="text-2xl text-slate-900 font-semibold mb-2">
                Keep Your Vehicle in Top Condition
              </h2>
              <p className="text-gray-800 leading-relaxed">
                Track all your maintenance records in one convenient place, set
                up customized reminders for upcoming services like oil changes
                and tire rotations, access detailed service history reports, and
                follow manufacturer-recommended maintenance schedules to ensure
                your vehicle maintains peak performance and reliability for
                years to come.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
