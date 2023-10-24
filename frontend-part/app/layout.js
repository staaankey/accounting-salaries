import './globals.scss';
import Link from 'next/link';
import { Poppins } from 'next/font/google';


const poppins = Poppins({
  weight: ['400', '900'],
  subsets: ['latin'],
});

export default function RootLayout({ children }) {
  return (
    <html lang="ua">
      <body className="body">
        <header className="header">
            <div className="container">
              <nav className="header__nav">
                <Link className="header__home" href="/">LTSNU Salary</Link>
                <Link className="header__link" href="/dep">Відділи</Link>
                <Link className="header__link" href="/admin">Адміністратори</Link>
                <Link className="header__link" href="/">Зарплати</Link>
              </nav>
            </div>
        </header>
        <div className="container">
          {children}
        </div>
      </body>
    </html>
  );
}
