from fastapi import FastAPI
from fastapi.responses import JSONResponse

app = FastAPI(
    title="HemoNexus ML Service",
    description="Machine Learning Service for Blood Demand Forecasting and Shortage Risk Assessment",
    version="1.0.0"
)


@app.get("/health")
async def health_check():
    """
    Health check endpoint to verify the ML service is running.
    """
    return {
        "status": "UP",
        "service": "hemonexus-ml-service"
    }


if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
