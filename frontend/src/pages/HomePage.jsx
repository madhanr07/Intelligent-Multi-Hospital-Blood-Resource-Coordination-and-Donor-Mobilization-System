import React, { useState, useEffect } from 'react';
import { healthCheck } from '../services/apiService';
import './HomePage.css';

function HomePage() {
  const [backendStatus, setBackendStatus] = useState('checking');
  const [healthData, setHealthData] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    checkBackendHealth();
  }, []);

  const checkBackendHealth = async () => {
    try {
      setBackendStatus('checking');
      const data = await healthCheck();
      setHealthData(data);
      setBackendStatus('connected');
      setError(null);
    } catch (err) {
      setBackendStatus('unavailable');
      setError('Unable to connect to backend');
      setHealthData(null);
    }
  };

  return (
    <div className="home-page">
      <div className="container py-5">
        <div className="text-center mb-5">
          <h1 className="display-4 fw-bold">HemoNexus</h1>
          <p className="lead text-muted">
            Intelligent Multi-Hospital Blood Resource Coordination and Donor Mobilization System
          </p>
        </div>

        <div className="card shadow-sm">
          <div className="card-body">
            <h3 className="mb-4">System Status</h3>
            
            <div className="status-section mb-4">
              <h5>Backend Status</h5>
              {backendStatus === 'checking' && (
                <div className="d-flex align-items-center">
                  <div className="spinner-border spinner-border-sm me-2" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                  <span>Checking...</span>
                </div>
              )}
              
              {backendStatus === 'connected' && (
                <div className="alert alert-success">
                  <strong>Backend Status: Connected</strong>
                  {healthData && (
                    <div className="mt-2">
                      <small>Service: {healthData.service}</small>
                    </div>
                  )}
                </div>
              )}
              
              {backendStatus === 'unavailable' && (
                <div className="alert alert-danger">
                  <strong>Backend Status: Unavailable</strong>
                  <div className="mt-2">
                    <small>{error}</small>
                  </div>
                  <button 
                    className="btn btn-sm btn-outline-danger mt-2"
                    onClick={checkBackendHealth}
                  >
                    Retry
                  </button>
                </div>
              )}
            </div>

            <div className="info-section">
              <h5>Development Status</h5>
              <div className="alert alert-info">
                <strong>Module 1 - Project Foundation</strong>
                <p className="mb-0 mt-2">
                  This is the foundation setup for the HemoNexus system. 
                  Business functionality will be implemented in subsequent modules.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default HomePage;
