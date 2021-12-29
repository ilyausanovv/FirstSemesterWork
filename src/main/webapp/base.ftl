<!DOCTYPE html>

<html lang="en">
<head>
    <title>Van Gogh Shop</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="icon" type="image/png" href="assets/icon.png">
    <link rel="stylesheet" type="text/css" href="/styles/startpage.css">
    <script src="scripts/registration.js"></script>

    <style>
        body {
            background: #171515 url(assets/background.png);
            color: #FFFFFF;
        }
        header {
            background: #171515 url(assets/background.png);
            color: #FFFFFF;
        }
    </style>

</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-darkblue navbar-expand-sm">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item mr-auto">
                <a><img src="assets/logo.png" id="logo" alt="mainLogo" style="width: 200px; height: 45px; margin-right: 20px"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light" href="/homepage">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light" href="/aboutuspage">About us</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light">8 (800) 550-04-79</a>
            </li>
        </ul>
        <#if username?has_content>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="/profile" class="nav-link text-light ">${username}</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="/shoppingcartpage.ftl" class="nav-link text-light">Shopping Cart</a>
                </li>
            </ul>
        <#else>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="/login" class="nav-link text-light ">Sign in</a>
                </li>
            </ul>
        </#if>
    </nav>
</header>
</body>
</html>