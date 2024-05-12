import React from 'react';
import { BsGeoAlt, BsTelephone, BsPeopleFill, BsTwitter, BsFacebook, BsInstagram, BsLinkedin } from 'react-icons/bs';

function Footer() {
    return (
        <footer id="footer" className="footer">
            <div className="container">
                <div className="row gy-3">
                    <div className="col-lg-3 col-md-6 d-flex">
                        <BsGeoAlt className="icon" />
                        <div>
                            <h4>Address</h4>
                            <p>
                                Rruga Xhevdet Doda <br />
                                Prishtine, PR 10000 - RKS<br />
                            </p>
                        </div>
                    </div>

                    <div className="col-lg-3 col-md-6 footer-links d-flex">
                        <BsTelephone className="icon" />
                        <div>
                            <h4>Contact</h4>
                            <p>
                                <strong>Phone:</strong> +38349123123<br />
                                <strong>Email:</strong> bookbar-info@email.com<br />
                            </p>
                        </div>
                    </div>

                    <div className="col-lg-3 col-md-6 footer-links d-flex">
                        <BsPeopleFill className="icon" />
                        <div>
                            <h4>Team</h4>
                            <p>
                                Elsa Rama, Harris Çmega<br />
                                Ardit Mirena, Blendi Gjoni
                            </p>
                        </div>
                    </div>

                    <div className="col-lg-3 col-md-6 footer-links">
                        <h4>Follow Us</h4>
                        <div className="social-links d-flex">
                            <a href="#" className="twitter"><BsTwitter /></a>
                            <a href="#" className="facebook"><BsFacebook /></a>
                            <a href="#" className="instagram"><BsInstagram /></a>
                            <a href="#" className="linkedin"><BsLinkedin /></a>
                        </div>
                    </div>
                </div>
            </div>

            <div className="container">
                <div className="copyright">
                    &copy; Copyright <strong><span>BookBar</span></strong>. All Rights Reserved
                </div>
            </div>
        </footer>
    );
}

export default Footer;
