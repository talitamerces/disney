(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 22-3. Full-text query example :)

for $b score $s in /books/book[content ftcontains ("web site" weight 0.2)
                                                   && ("usability" weight 0.8)]
where $s > 0.5
order by $s descending
return <result>
          <title> {$b//title} </title>
          <score> {$s} </score>
       </result>

