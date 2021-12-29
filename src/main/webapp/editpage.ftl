<#include 'base.ftl'>

<div class="row py-5 px-4">
    <div class="col-md-5 mx-auto">
        <div class="bg-white shadow rounded overflow-hidden">
            <form class="form" method="post" action="/changeEmail">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                           placeholder="Email" name="email">
                </div>
                <div style="text-align: center;">
                    <button id="add-email" class="btn">Add</button>
                </div>
            </form>
            <form class="form" method="post" action="/changeUsername">
                <div class="form-group">
                    <label for="username">Login</label>
                    <input class="form-control" id="username" placeholder="New login" onblur="checkUsername()" name="new-username">
                    <p class="error" id="username-error" style="display: none; color: red">Login already taken</p>
                    <p class="error" id="username-size-error" style="display: none; color: red">Login is too short</p>
                </div>
                <div style="text-align: center;">
                    <button id="lgn" class="btn" disabled="disabled" style="">Replace</button>
                </div>
            </form>
            <style>
                .form {
                    padding: 20px;
                }
            </style>
        </div>
    </div>
</div>