/* query to show all the rates with the content showed in settlement website */
select
d.name,
p.name,
p2.name,
r.description,
r.endprice,
r.vat,
r.priceexvat,
r.sharefactor,
r.rdmlshare,
r.operatorshare,
r.cpshare,
r.othercost,
r.minshare,
r.countfactor,
r.package,
r.validto,
b.procedurename
from rates r
join domains d on d.domain_id = r.clientid
join products p on p.id = r.productid
join portals p2 on p2.id = r.portalid
join billinginterface b on b.interfaceid = r.interfaceid
where r.portalid = 138
order by clientid;