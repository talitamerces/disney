(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 24-5. SQL/XML query :)

SELECT c.number,
       XMLELEMENT ( NAME "product",
                    XMLATTRIBUTES (
                      c.dept AS "dept",
                      c.name AS "prodname",
                   ) AS "product_as_xml"
FROM catalog c;
