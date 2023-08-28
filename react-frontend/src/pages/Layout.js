import { Outlet, Link } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <nav>
        <ul>
          <li>
            <Link to="/">Домашня сторінка</Link>
          </li>
          <li>
            <Link to="/departments">Департаменти</Link>
          </li>
          <li>
            <Link to="/users">Користувачі</Link>
          </li>
        </ul>
      </nav>

      <Outlet />
    </>
  )
};