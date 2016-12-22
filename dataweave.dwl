<product id="1" type="tv">
  <brand>Samsung</brand>
</product>



%dw 1.0
%output application/json
---
{
  item: {
    type : payload.product.@type,
    name : payload.product.brand,
    attributes: payload.product.@
  }
}