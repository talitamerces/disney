(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-7. Computed document constructor :)

document {
  element product {
    attribute dept { "ACC" },
    element number { 563 },
    element name { attribute language {"en"}, "Floppy Sun Hat"}
  }
}
