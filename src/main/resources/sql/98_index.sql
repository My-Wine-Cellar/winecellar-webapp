-- region_area
CREATE INDEX idx_region_area_region_id ON region_area (region_id);

-- review
CREATE INDEX idx_review_user_wine ON review (user_id, wine_id);
