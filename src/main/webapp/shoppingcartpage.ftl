<#include 'base.ftl'>

<link rel="stylesheet" type="text/css" href="/styles/shoppingcartpage.css">
    <table>
<thead>
<tr>
    <td class="table-name">Name of commodity</td>
    <td class="table-count">Number</td>
    <td class="table-price">Price per unit</td>
</tr>
</thead>
    <tbody>
<#if username?has_content>
    <#if commodities?has_content>
        <#list commodities as commodity>
            <tr>
                <td class="table-name">${commodity.getName()}</td>
                <td class="table-count">1</td>
                <form method="post" action="/deleteFromShoppingCart?id=${commodity.getId()}">
                    <td class="table-price">${commodity.getPrice()} dollars <button class="delete">Delete</button></td>
                </form>
            </tr>
        </#list>
        </tbody>
        </table>
        <form action="/shoppingcartservlet" method="post">
            <div style="text-align: center;"><button class="buy_btn">Make a purchase</button></div>
        </form>
    <#else>
        <div style="text-align: center; margin-bottom: 10px"><h2>Add the commodity to your shopping cart!</h2></div>
    </#if>
<#else>
    <div style="text-align: center">
        <a class='login' href="/login">To purchase, you need to log in!</a>
    </div>
</#if>