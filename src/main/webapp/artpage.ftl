<#include 'base.ftl'>
<style>
    .bg-dark {
        background-color: #484545;
    }
    .img-thumbnail {
        background-color: #a7a2a2;
    }
</style>
<br>
<div class="row py-5 px-4">
    <div class="col-md-5 mx-auto">
        <div class="bg-dark shadow rounded overflow-hidden">
            <div class="px-4 pt-0 pb-4 cover">
                <div style="text-align: center;">
                    <div class="block">
                        <div class="profile"><img src="${art.getImage()}" alt="Painting photo" width="200" class="rounded mb-2 img-thumbnail">
                            <div class="media-body mb-5 text-black">
                                <h4 class="mt-0 mb-0">${art.getName()}</h4>
                            </div>
                        </div>
                    </div>
                    <h5 class="font-weight-bold mb-0 d-block">Price: ${art.getPrice()} $</h5><br>
                    <h6 class="font-weight-bold mb-0 d-block">Rating: ${art.getRating()}</h6>
                </div>
                <div class="px-4 py-3">
                    <h5 class="mb-0">About painting:</h5>
                    <div class="p-4 rounded shadow-sm bg-secondary">
                        <p class="font-weight-normal mb-0">${art.getDescription()}</p>
                    </div>
                </div>
                <form action="/addToShoppingCart?artd=${art.getId()}" method="post">
                    <div class="py-4 px-4">
                        <div class="d-flex align-items-center justify-content-between m-auto">
                            <button class="btn btn-outline-light btn-sm btn-block">Add to Shopping Cart</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>