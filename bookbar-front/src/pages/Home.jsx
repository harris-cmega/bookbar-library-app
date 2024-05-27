import Carousel from "../components/Carousel.jsx";
import CallToAction from "../components/CallToAction.jsx";
import Layout from '../components/layouts/Layout.jsx';

const Home = () => {
    return (
        <Layout>
            <CallToAction />
            <Carousel />
        </Layout>
    );
}
export default Home;