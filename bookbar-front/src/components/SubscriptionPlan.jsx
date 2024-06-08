import React, { useState, useContext } from 'react';
import ApiService from '../api/ApiService';

const SubscriptionPlan = () => {
    const [selectedPlan, setSelectedPlan] = useState(null);

    const handleSelectPlan = async (planId) => {
        try {
            const currentDate = new Date();
            const endDate = new Date(currentDate.setDate(currentDate.getDate() + 30)).toISOString().slice(0, 10);
            const newSubscription = {
                userId: user.id,
                subscriptionId: planId,
                startDate: new Date().toISOString().slice(0, 10),
                endDate: endDate
            };
            await ApiService.createUserSubscription(newSubscription);
            alert('Subscription created successfully!');
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
                            <li>Feature</li>
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
                            <li>Feature</li>
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
                            <li>Feature</li>
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