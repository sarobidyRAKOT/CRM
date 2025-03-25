
SELECT
    customer_id,
    SUM (montant) AS montant
FROM budget
GROUP BY customer_id;

SELECT
    depense_id,
    MAX(daty) daty
FROM montant_dl
GROUP BY depense_id

SELECT
    dl.*
FROM montant_dl AS dl 
WHERE depense_id IN (
    SELECT
        depense_id,
        MAX(daty) daty
    FROM montant_dl
    GROUP BY depense_id
)


SELECT 
    depense_id, 
    MAX(daty) AS daty, 
    montant
FROM montant_dl
GROUP BY depense_id, montant
ORDER BY depense_id;

SELECT depense_id, montant, daty
FROM (
    SELECT depense_id, montant, daty, 
           ROW_NUMBER() OVER (PARTITION BY depense_id ORDER BY daty DESC) AS rn
    FROM montant_dl
) t
WHERE rn = 1;

-- ********************************************
-- lead
CREATE OR REPLACE VIEW v_depense_lead AS
SELECT 
    dl.lead_id,
    dl.customer_id,
    dp.depense_id, 
    dp.montant, 
    dp.daty
FROM depense_lead AS dl
LEFT JOIN (
	SELECT depense_id, montant, daty
	FROM (
	    SELECT depense_id, montant, daty, 
	           ROW_NUMBER() OVER (PARTITION BY depense_id ORDER BY daty DESC) AS rn
	    FROM montant_dl
	) t
	WHERE rn = 1
) AS dp ON dl.depense_id = dp.depense_id 
WHERE dl.lead_id IN (
    -- pour enlever les lead sup
    SELECT 
        l.lead_id
    FROM trigger_lead AS l 
    WHERE NOT EXISTS (
        SELECT 1 FROM lead_sup AS ls WHERE ls.lead_id = l.lead_id
    )
);
-- ticket 
CREATE OR REPLACE VIEW v_depense_ticket AS
SELECT 
    dt.ticket_id,
    dt.customer_id,
    dp.depense_id, 
    dp.montant, 
    dp.daty
FROM depense_ticket AS dt
LEFT JOIN (
	SELECT depense_id, montant, daty
	FROM (
	    SELECT depense_id, montant, daty, 
	           ROW_NUMBER() OVER (PARTITION BY depense_id ORDER BY daty DESC) AS rn
	    FROM montant_dt
	) t
	WHERE rn = 1
) AS dp ON dt.depense_id = dp.depense_id 
WHERE dt.ticket_id IN (
    -- pour enlever les ticket sup
    SELECT 
        t.ticket_id
    FROM trigger_ticket AS t 
    WHERE NOT EXISTS (
        SELECT 1 FROM ticket_sup AS ts WHERE ts.ticket_id = t.ticket_id
    )
);

-- ***********************************************

-- lead non supprimer
SELECT 
    l.lead_id
FROM trigger_lead AS l 
WHERE NOT EXISTS (
    SELECT 1 FROM lead_sup AS ls WHERE ls.lead_id = l.lead_id
);


SELECT 
    t.ticket_id
FROM trigger_ticket AS t 
WHERE NOT EXISTS (
    SELECT 1 FROM ticket_sup AS ts WHERE ts.ticket_id = t.ticket_id
);



SELECT 
*
FROM v_depense_lead AS dl
GROUP BY customer_id