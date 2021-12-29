<#include 'base.ftl'>

<html lang="en">
<div class="row py-5 px-4">
    <div class="col-md-5 mx-auto">
        <div class="bg-white shadow rounded overflow-hidden text-dark" style=".text-dark{color: #aa7b7c}">
            <div class="px-4 pt-0 pb-4 cover">
                <div class="media align-items-end profile-head">
                    <div class="profile mr-3"><img src="assets/profilepic.jpg" alt="..." width="130" class="rounded mb-2 img-thumbnail">
                        <a href="/editpage" class="btn btn-outline-dark btn-sm btn-block" style="border-radius: 15px">Change profile</a></div>
                    <div class="media-body mb-5 text-black">
                        <h4 class="mt-0 mb-0"><strong>${username}</strong></h4>
                    </div>
                </div>
            </div>
            <div class="bg-light p-4 d-flex justify-content-start text-center">
                <ul class="list-inline mb-0">
                    <li class="list-inline-item">
                        <h5 class="font-weight-bold mb-0 d-block">Pictures purchased: ${ordersCount}</h5><small class="text-muted"></small>
                    </li>
                </ul>
            </div>
            <div class="px-4 py-3">
                <h5 class="mb-0">Information about user</h5>
                <div class="p-4 rounded shadow-sm bg-light">
                    <p class="font-weight-normal mb-0">Username: <strong>${username}</strong></p>
                    <p class="font-weight-normal  mb-0">Email: <strong>${email}</strong></p>

                </div>
            </div>
            <div class="py-4 px-4">
                <div class="d-flex align-items-center justify-content-between mb-3 ">
                    <h5 class="mb-0">Last purchased painting: </h5>
                </div>
                <div class="row">
                    <div class="col-lg-6 mb-2 pr-lg-1">
                        <h5 class="mb-0"><strong>${lastProductName}</strong></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>