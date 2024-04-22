import Carousel from "../components/Carousel.jsx";
import CallToAction from "../components/CallToAction.jsx";

const Home = () => {
    return(
        <div>
            {window.location.pathname === '/' && <CallToAction />}
            {window.location.pathname === '/' && <Carousel />}
        </div>
    )
}
export default Home;