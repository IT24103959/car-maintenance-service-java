import type { Metadata } from "next";
import Link from "next/link"; // Import Link component
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Auto Alchemist",
  description: "Auto Alchemist is a car repair shop management system.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <nav className="absolute top-0 left-0 w-full bg-gray-800 text-white p-4 shadow-md z-50">
          <ul className="flex space-x-4 justify-end">
            <li>
              <Link
                href="/admin"
                className="hover:text-red-500 transition-colors duration-300"
              >
                Admin
              </Link>
            </li>
            <li>
              <Link
                href="/employees"
                className="hover:text-red-500 transition-colors duration-300"
              >
                Employees
              </Link>
            </li>
            <li>
              <Link
                href="/vehicles"
                className="hover:text-red-500 transition-colors duration-300"
              >
                Vehicles
              </Link>
            </li>
            <li>
              <Link
                href="/services"
                className="hover:text-red-500 transition-colors duration-300"
              >
                Services
              </Link>
            </li>
            <li>
              <Link
                href="/reviews"
                className="hover:text-red-500 transition-colors duration-300"
              >
                Reviews
              </Link>
            </li>
          </ul>
        </nav>
        <div>{children}</div>
      </body>
    </html>
  );
}
