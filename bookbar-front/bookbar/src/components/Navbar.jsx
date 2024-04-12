const Navbar = () => {
  return (
    <nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
    data-bs-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">BookBar</a>
    <div class="collapse navbar-collapse" id="navbarExample">
      <ul class="navbar-nav me-auto mb-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">Books</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">Accessories</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">eBooks</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">Locations</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Categories</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider" /></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
      <div class="d-flex align-items-center flex-column flex-lg-row">
        <form class="me-2 mb-2 mb-lg-0">
          <input type="text" class="form-control form-control-sm" placeholder="Search by Title, Author, Keyword or ISBN" />
        </form>
        <a class="btn btn-primary" href="">Sign up</a>
      </div>
    </div>
  </div>
</nav>
  )
};

export default Navbar;