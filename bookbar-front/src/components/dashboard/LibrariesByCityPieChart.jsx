import React, { useEffect, useState } from 'react';
import { Pie } from 'react-chartjs-2';
import { Chart, ArcElement, Tooltip, Legend } from 'chart.js';
import ApiService from '../../api/ApiService';

// Register the required components
Chart.register(ArcElement, Tooltip, Legend);

const LibrariesByCityPieChart = () => {
    const [chartData, setChartData] = useState(null);

    useEffect(() => {
        fetchLibraryData();
    }, []);

    const fetchLibraryData = async () => {
        try {
            const response = await ApiService.getLibraries();
            const libraries = response.data;

            const cityCounts = libraries.reduce((acc, library) => {
                const city = library.city || 'Unknown';
                acc[city] = (acc[city] || 0) + 1;
                return acc;
            }, {});

            const labels = Object.keys(cityCounts);
            const data = Object.values(cityCounts);

            setChartData({
                labels,
                datasets: [
                    {
                        label: 'Libraries by City',
                        data,
                        backgroundColor: [
                            '#FF6384',
                            '#36A2EB',
                            '#FFCE56',
                            '#4BC0C0',
                            '#9966FF',
                            '#FF9F40'
                        ]
                    }
                ]
            });
        } catch (error) {
            console.error('Error fetching libraries:', error);
        }
    };

    return (
        <div>
            <h2>Libraries by City</h2>
            {chartData ? <div style={{ width: '30%', height: '30%' }}><Pie data={chartData} /></div> : <p>Loading...</p>}
        </div>
    );
};

export default LibrariesByCityPieChart;
