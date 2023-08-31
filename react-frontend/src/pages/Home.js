import React, { useEffect, useState } from 'react'
import logo from '../logo_color.png';
import '../Home.css'


export default function Home() {
  return (
    <div className="Home">
      <header className="Home-header">
        <img src={logo} className="Home-logo" alt="logo" />
      </header>
    </div>
  )
}
