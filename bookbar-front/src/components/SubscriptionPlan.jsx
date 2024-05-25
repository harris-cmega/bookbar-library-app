import React, { useState, useContext } from 'react';

const SubscriptionPlan = () => {
    return (
        <section className="vh-100 d-flex align-items-center justify-content-center">
            <div class="container">
            <div class="row gutters">
                <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6">
                    <div class="pricing-plan">
                        <div class="pricing-header">
                            <h4 class="pricing-title">Classic</h4>
                            <div class="pricing-cost">9.99€</div>
                            <div class="pricing-save text-warning">Save 19.99€</div>
                        </div>
                        <ul class="pricing-features">
                            <li>Up to 3 free book reading experiences</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li class="text-muted"><del>Feature</del></li>
                            <li class="text-muted"><del>Feature</del></li>
                        </ul>
                        <div class="pricing-footer">
                            <a href="#" class="btn btn-primary btn-lg">Select Plan</a>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-6 col-sm-6">
                    <div class="pricing-plan">
                        <div class="pricing-header red">
                            <h4 class="pricing-title">Priemium</h4>
                            <div class="pricing-cost">29.99€</div>
                            <div class="pricing-save text-warning">Save 59.99€</div>
                        </div>
                        <ul class="pricing-features">
                            <li>Up to 10 free book reading experiences</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li class="text-muted"><del>Feature</del></li>
                        </ul>
                        <div class="pricing-footer">
                            <a href="#" class="btn btn-danger btn-lg">Select Plan</a>
                        </div>
                    </div>
                </div>
                <div class="col-xl-4 col-lg-4 col-md-12 col-sm-12">
                    <div class="pricing-plan">
                        <div class="pricing-header secondary">
                            <h4 class="pricing-title">Special</h4>
                            <div class="pricing-cost">49.99€</div>
                            <div class="pricing-save text-warning">Save 99.99€</div>
                        </div>
                        <ul class="pricing-features">
                            <li>Up to 30 free book reading experiences</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                            <li>Feature</li>
                        </ul>
                        <div class="pricing-footer">
                            <a href="#" class="btn btn-success btn-lg">Select Plan</a>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </section>
    )
}

export default SubscriptionPlan;