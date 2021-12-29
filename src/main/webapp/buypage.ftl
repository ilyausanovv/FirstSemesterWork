<#include 'base.ftl'>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/styles/shoppingcartpage.css.css">
<br>
<div style="text-align: center;"><h1>Congratulations on your purchase!</h1></div>
<form method="post" action="/buypage">
    <table>
        <thead>
        <tr>
            <td class="table-name">Name of commodity</td>
            <td class="table-price">Grade</td>
        </tr>
        </thead>
        <tbody>
        <#list commodities as commodity>
            <tr>
                <td class="table-name">${commodity.getName()}</td>
                <td class="table-price">
                    <select name="evaluation${commodity.getId()}" id="evaluation${commodity.getId()}">
                        <option disabled>Choose grade</option>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    <label for="evaluation${commodity.getId()}">Points</label></td>
            </tr>
        </#list>
        </tbody>

    </table>
    <div style="text-align: center;"><button id="marks" type="submit" class="btn">Rate</button></div>
</form>