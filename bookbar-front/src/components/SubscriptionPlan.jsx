import React, { useState, useContext, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode'; 
import ApiService from '../api/ApiService';

const SubscriptionPlan = () => {
    const [selectedPlan, setSelectedPlan] = useState(null);
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');

        if (token) {
            try {
                const decodedToken = jwtDecode(token);

                const username = decodedToken.sub;
                fetchUserId(username);
            } catch (error) {
                console.error('Error decoding token:', error);
            }
        }
    }, []);

    const fetchUserId = async (username) => {
        try {
            // Call your API to fetch all users
            const response = await ApiService.getUsers();
            const users = response.data;
            const user = users.find(user => user.username === username);
            if (user) {
                setUserId(user.id);
            } else {
                console.warn('User not found for the username:', username);
            }
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const handleSelectPlan = async (planId) => {
        if (!userId) {
            alert('User ID not found. Please log in.');
            return;
        }

        try {
            const currentDate = new Date();
            const startDate = currentDate.toISOString();
            const endDate = new Date(currentDate.setDate(currentDate.getDate() + 30)).toISOString();
            
            const newSubscription = {
                userId: userId,
                subscriptionId: planId,
                startDate: startDate,
                endDate: endDate
            };

            const response = await ApiService.createSubscriptionForUser(userId, newSubscription);

            alert('Your subscription has started successfully!');
        } catch (error) {
            console.error('Error creating subscription:', error);
            alert('Failed to create subscription.');
        }
    };

    return (
        <section className="vh-100 d-flex align-items-center justify-content-center">
            <div className="container">
            <div className="row gutters">
                <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6">
                    <div className="pricing-plan">
                        <div className="pricing-header">
                            <h4 className="pricing-title">Classic</h4>
                            <div className="pricing-cost">9.99€</div>
                            <div className="pricing-save text-warning">Save 19.99€</div>
                        </div>
                        <ul className="pricing-features">
                            <li>Up to 3 free book reading experiences</li>
                            <li>Up to 2 hours free reading experiences</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li className="text-muted"><del>Feature</del></li>
                            <li className="text-muted"><del>Feature</del></li>
                        </ul>
                        <div className="pricing-footer">
                            <button className="btn btn-primary btn-lg" onClick={() => handleSelectPlan(1)}>Select Plan</button>
                        </div>
                    </div>
                </div>
                <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6">
                    <div className="pricing-plan">
                        <div className="pricing-header red">
                            <h4 className="pricing-title">Priemium</h4>
                            <div className="pricing-cost">29.99€</div>
                            <div className="pricing-save text-warning">Save 59.99€</div>
                        </div>
                        <ul className="pricing-features">
                            <li>Up to 10 free book reading experiences</li>
                            <li>Up to 8 hours free reading experiences</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li className="text-muted"><del>Feature</del></li>
                        </ul>
                        <div className="pricing-footer">
                            <button className="btn btn-danger btn-lg" onClick={() => handleSelectPlan(2)}>Select Plan</button>
                        </div>
                    </div>
                </div>
                <div className="col-xl-4 col-lg-4 col-md-12 col-sm-12">
                    <div className="pricing-plan">
                        <div className="pricing-header secondary">
                            <h4 className="pricing-title">Special</h4>
                            <div className="pricing-cost">49.99€</div>
                            <div className="pricing-save text-warning">Save 99.99€</div>
                        </div>
                        <ul className="pricing-features">
                            <li>Up to 30 free book reading experiences</li>
                            <li>Up to 24 hours free reading experiences</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                        </ul>
                        <div className="pricing-footer">
                            <button className="btn btn-success btn-lg" onClick={() => handleSelectPlan(3)}>Select Plan</button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </section>
    )
}

export default SubscriptionPlan;